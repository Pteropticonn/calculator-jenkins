pipeline {
    agent any
        environment {
            DOCKERHUB_CREDENTIALS = credentials('pteropticon-dockerhub')
        }
        stages {
            stage("Compile") {
                steps {
                    sh "./gradlew compileJava"
                }
            }
            stage("Unit test") {
                steps {
                    sh "./gradlew test"
            }
        }
            stage("Code Coverage") {
                steps {
                    sh "./gradlew jacocoTestReport"
                    publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                    ])
                    sh "./gradlew jacocoTestCoverageVerification"
                }
            }
           stage("Static code analysis") {
                steps {
                    sh "./gradlew checkstyleMain"
                    publishHTML (target: [
                        reportDir: 'build/reports/checkstyle/',
                        reportFiles: 'main.html',
                        reportName: "Checkstyle Report"
                    ])
                }
           }
           stage("Package") {
                steps {
                     sh "./gradlew build"
                }
           }
           stage("Docker build") {
                steps {
                 sh "docker build -t pteropticon/calculator ."
              }
          }
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
          stage("Docker push") {
             steps {
                sh "docker push pteropticon/calculator"
                }
          }
    }
      post {
        always {
          sh 'docker logout'
        }
}
}