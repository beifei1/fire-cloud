pipeline {
    agent any

//    triggers { pollSCM('H/1 0 * * *')}
    tools {
        maven 'maven-3.10.0'
        jdk 'JDK8'
    }
    options { buildDiscarder(logRotator(numToKeepStr: '3'))}

    environment {
        _github_credentialsId = 'dcae8179-aec2-4eb5-b6ce-177179d463c5'
        _deploy_to_nexus = "${params.deploy_nexus}"
        _build_state_notify_from = "wangzhichaomin@163.com"
        _build_state_notify_to = "wangzhichao03@tojoy.com"
    }

    parameters {
        choice(name:'deploy_nexus',choices:'no\nyes',description:'是否发布制品到Nexus')
        choice(name:'node_env',choices:'dev\ntest\nprod',description:'机器环境')
        string(defaultValue: 'https://github.com/beifei1/fire-cloud.git', name: 'repo_addr', description: 'Git仓库路径')
        string(defaultValue: "${env.JOB_NAME}", name: "project_name", description: "项目名称")
        string(defaultValue: "${env.JOB_NAME}/pom.xml", name: "pom_path", description: "Pom文件在Jenkins workspace中的相对路径")
        string(defaultValue: 'master', name: 'repo_branch', description: 'Git 分支')
    }

    stages {
        stage('fetch code') {
            steps {
                echo "staring fetch code from ${params.repo_addr}..."
                git credentialsId: "${_github_credentialsId}", url: "${params.repo_addr}", branch: "${params.repo_branch}"
                echo "fetch code complete !"
            }
        }

        stage('代码质量检查') {steps {echo '配合sonar'} }

        stage ("构建安装") {
            when {equals expected: 'no', actual: _deploy_to_nexus}
            steps {
               configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
                   sh "mvn -f ${params.pom_path} -s $MAVEN_GLOBALE_SETTING package -Dmaven.test.skip=true"
               }
            }
        }

        stage ("构建发布") {
            when {equals expected: 'yes', actual: _deploy_to_nexus}
            steps {;
               configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
                   sh "mvn -f ${params.pom_path} -s $MAVEN_GLOBALE_SETTING  deploy -Dmaven.test.skip=true"
               }
            }
        }
        stage('单元测试') {steps {echo '配合junit'} }
        stage('覆盖率检测') {steps {echo '配合jacoco'} }
        stage('性能测试') {steps {echo '配合jmeter'}}

        stage('应用部署') {
            steps {
                ansiblePlaybook(playbook: "${env.WORKSPACE}/deploy/${params.project_name}.yml", inventory: "${env.WORKSPACE}/deploy/inventory/${params.node_env}/hosts", credentialsId: '89533194-9774-4444-b42b-c9362a308b1b')
            }
        }
    }
    post {
        always {
            echo "clean jenkins workspace"
            cleanWs()
        }
        failure {
            sendNotifyMail(subjectKeyword: "失败");
        }
        success {
            sendNotifyMail(subjectKeyword: "成功")
        }
    }
}

def sendNotifyMail(subjectKeyword) {
    emailext (
         body: '''${FILE, path="/var/lib/jenkins/notify.html"}''',
         mimeType: 'text/html',
         subject: "[Pipeline][${JOB_NAME}]" + subjectKeyword + ": - #${BUILD_NUMBER}!",
         to: "${_build_state_notify_to}",
         from: "${_build_state_notify_from}"
    )
}