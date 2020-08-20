pipeline {
    agent any

    triggers { pollSCM('H/1 0 * * *')}
    tools {
        maven 'maven-3.10.0'
        jdk 'JDK8'
    }
    options { buildDiscarder(logRotator(numToKeepStr: '3'))}

    environment {
        _github_credentialsId = 'dcae8179-aec2-4eb5-b6ce-177179d463c5'
        _deploy_to_nexus = "${params.deploy}"
        _build_state_notify_email = "wangzhichao03@tojoy.com"
        _build_state_notify_template = "/var/lib/jenkins/notify.html"
    }

    parameters {
        string(defaultValue: "fire-gateway/pom.xml", name:'pomPath', description: 'pom文件相对路径')
        string(defaultValue: 'https://github.com/beifei1/fire-cloud.git', name: 'repoUrl', description: '代码仓库路径')
        string(defaultValue: 'master', name: 'repoBranch', description: '拉取的代码分支')
        choice(name:'deploy',choices:'False\nTrue',description:'是否发布到私服')
    }

    stages {
        stage('代码获取') {
            steps {
                echo "staring fetch code from ${params.repoUrl}..."
                git credentialsId: "${_github_credentialsId}", url: "${params.repoUrl}", branch: "${params.repoBranch}"
                echo "fetch code complete !"
            }
        }

        stage('代码质量检测') {steps {echo '配合sonar'} }

        stage ("构建及安装") {
            when {equals expected: 'False', actual: _deploy_to_nexus}
            steps {
               configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
                   sh "mvn -f ${params.pomPath} -s $MAVEN_GLOBALE_SETTING install -Dmaven.skip.test=true"
               }
            }
        }

        stage ("构建及发布") {
            when {equals expected: 'True', actual: _deploy_to_nexus}
            steps {
               configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
                   sh "mvn -f ${params.pomPath} -s $MAVEN_GLOBALE_SETTING deploy -Dmaven.skip.test=true"
               }
            }
        }
        stage('单元测试') {steps {echo '配合junit'} }
        stage('覆盖率检测') {steps {echo '配合jacoco'} }
        stage('性能测试') {steps {echo '配合jmeter'}}

        stage('应用部署') {
            steps {
                ansiblePlaybook(playbook: "${env.WORKSPACE}/deploy/playbook.yml", inventory: "${env.WORKSPACE}/deploy/hosts", credentialsId: '89533194-9774-4444-b42b-c9362a308b1b')
            }
        }
    }
    post {
        always {
            echo "Start cleaning workspace"
            cleanWs()
        }
        failure {
            emailext (
                body: '${FILE, path="${_build_state_notify_template}"}',
                mimeType: 'text/html',
                subject: "[Jenkins]构建失败: ${JOB_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!",
                to: "${_build_state_notify_email}"
            )
        }
        success {
            emailext(
                body: '${FILE, path="${_build_state_notify_template}"}',
                mimeType: 'text/html',
                subject: "[Jenkins]构建成功: ${JOB_NAME} - Build # ${BUILD_NUMBER} - ${BUILD_STATUS}!",
                to: "${_build_state_notify_email}"
            )
        }
    }
}