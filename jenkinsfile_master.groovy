stage('pull source code') {
    node('master'){
        git([url: 'https://github.com/xiaochengyez/my_first_spring.git', branch: 'master'])
    }
}

stage('maven compile & package') {
    node('master'){
        sh ". /etc/profile"
        sh ". ~/.bash_profile"

        //定义maven java环境
        def mvnHome = tool 'maven-3.6.0_master'
        def jdkHome = tool 'jdk1.8_master'
        env.PATH = "${mvnHome}/bin:${env.PATH}"
        env.PATH = "${jdkHome}/bin:${env.PATH}"
        sh "mvn clean install"
        sh "mv target/iWeb.war target/ROOT.war"
    }
}

stage('clean docker environment') {
    node('master'){
        try{
            sh 'docker stop myWebObj'
        }catch(exc){
            echo 'myWebObj container is not running!'
        }

        try{
            sh 'docker rm myWebObj'
        }catch(exc){
            echo 'myWebObj container does not exist!'
        }
        try{
            sh 'docker rmi myweb'
        }catch(exc){
            echo 'myweb image does not exist!'
        }
    }
}

stage('make new docker image') {
    node('master'){
        try{
            sh 'docker build -t myweb .'
        }catch(exc){
            echo 'Make iweb docker image failed, please check the environment!'
        }
    }
}

stage('start docker container') {
    node('master'){
        try{
            sh 'docker run --name myWebObj -d -p 8111:8080 myweb'
        }catch(exc){
            echo 'Start docker image failed, please check the environment!'
        }
    }
}