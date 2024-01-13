pipeline {
    agent none
    options { skipDefaultCheckout(false) }
    stages {
        stage('Github') {
           agent any
           steps {
               git branch: 'main', url: 'https://github.com/Mins00oo/spring-study.git'
           }
        }
        stage('build') {
            agent any
            steps {
                sh "./gradlew clean build"
            }
            post {
                success {
                    echo 'gradle build success'
                }

                failure {
                    echo 'gradle build failed'
                }
            }
        }
    }
}