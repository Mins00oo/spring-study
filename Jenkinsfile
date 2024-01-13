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
                sh "chmod +x gradlew"
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
        stage('Docker Build') {
            agent any
            steps {
                sh 'docker build -t user-service:latest /var/jenkins_home/workspace/be_user'
            }
            post {
                success {
                    sh 'echo "Bulid Docker Image Success"'
                }

                failure {
                    sh 'echo "Bulid Docker Image Fail"'
                }
            }
        }
        stage('Deploy') {
            agent any
            steps {
                sh 'docker ps -f name=user-service -q \
                | xargs --no-run-if-empty docker container stop'

                sh 'docker container ls -a -f name=user-service -q \
        | xargs -r docker container rm'

                sh 'docker images -f dangling=true && \
                docker rmi $(docker images -f dangling=true -q)'

                sh 'docker run -d --name user-service \
                -p 8000:8000 \
                -v /etc/localtime:/etc/localtime:ro \
                user-service:latest'
            }

            post {
                success {
                    echo 'success'
                }

                failure {
                    echo 'failed'
                }
            }
        }
    }
}