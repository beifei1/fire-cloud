pipeline {
    agent any

      //触发器，可以根据需要自己选择，包括定时触发，定时去SCM拉取更新，由更新触发，或者由上游任务触发，或者由Gitlab主动调用jenkins暴漏的webhook触发
//    triggers {
//       cron('0 0 * * *')
//       pollSCM('H/1 0 * * *')
//       upstream(upstreamProjects: 'fire-user-provider,fire-user-consumer', threshold: hudson.model.Result.SUCCESS)
//       gitlab(triggerOnPush: true, triggerOnMergeRequest: false, branchFilterType: 'All')
//    }
    //执行Pipeline需要的工具，需要配合Jenkins全局工具配置使用
    tools {
        maven 'maven-3.10.0'
        jdk 'JDK8'
    }
    //保留的构建个数，对应手工配置里的参数
    options { buildDiscarder(logRotator(numToKeepStr: '3'))}

    //环境变量
    environment {
        _github_credentialsId = 'dcae8179-aec2-4eb5-b6ce-177179d463c5'
        _deploy_to_nexus = "${params.deploy_nexus}"
        _build_state_notify_from = "wangzhichaomin@163.com"
        _build_state_notify_to = "wangzhichao03@aaaa.com"
    }

    //参数化构建,效果就是在点击构建按钮后，后增加一步填写参数的步骤，脚本根据参数设置进行构建
    parameters {
        choice(name:'deploy_nexus',choices:'False\nTrue',description:'是否发布制品到Nexus')
        choice(name:'node_env',choices:'dev\ntest\nprod',description:'机器环境')
        string(defaultValue: 'https://github.com/beifei1/fire-cloud.git', name: 'repo_addr', description: 'Git仓库路径')
        string(defaultValue: "${env.JOB_NAME}", name: "project_name", description: "项目名称")
        string(defaultValue: "${env.JOB_NAME}/pom.xml", name: "pom_path", description: "Pom文件在Jenkins workspace中的相对路径")
        string(defaultValue: 'master', name: 'repo_branch', description: 'Git 分支')
    }

    //从git上拉取代码, credentialsId在jenkins的凭证管理里获取
    stages {
        stage('同步代码') {
            steps {
                echo "start fetching code from ${params.repo_addr}..."
                git credentialsId: "${_github_credentialsId}", url: "${params.repo_addr}", branch: "${params.repo_branch}"
                echo "fetching code completed !"
            }
        }

        //集成了配置文件管理，进行应用打包
        stage ("构建打包") {
            //when不能直接调用参数化构建里定义的参数，需要在环境变量中接收一下
            when {equals expected: 'False', actual: _deploy_to_nexus}
            steps {
               configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
                   sh "mvn -f ${params.pom_path} -s $MAVEN_GLOBALE_SETTING package -Dmaven.test.skip=true"
               }
            }
        }

        //这里根据选择的参数是仅打包还是同时发布到私服
        stage ("构建打包发布") {
            when {equals expected: 'True', actual: _deploy_to_nexus}
            steps {;
               configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
                   sh "mvn -f ${params.pom_path} -s $MAVEN_GLOBALE_SETTING  deploy -Dmaven.test.skip=true"
               }
            }
        }

        //集成ansiable进行应用部署
        stage('应用部署') {
            steps {
                ansiblePlaybook(playbook: "${env.WORKSPACE}/deploy/playbook/${params.project_name}.yml", inventory: "${env.WORKSPACE}/deploy/inventory/${params.node_env}/hosts", credentialsId: '89533194-9774-4444-b42b-c9362a308b1b')
            }
        }
    }
    post {
        always {
            cleanWs()  //jenkinsfile自带的函数，每次构建后清理构建空间
        }
        failure {
            sendNotifyEmail("失败");
        }
        success {
            sendNotifyEmail("成功")
        }
    }
}

//发送通知邮件
def sendNotifyEmail(subjectKeyword) {
    emailext (
         body: '''${FILE, path="/var/lib/jenkins/notify.html"}''',
         mimeType: 'text/html',
         subject: "[Pipeline][${JOB_NAME}]" + subjectKeyword + ": - #${BUILD_NUMBER}!",
         to: "${_build_state_notify_to}",
         from: "${_build_state_notify_from}"
    )
}