pipeline {
    agent any
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
                 sh "docker build -t Pteropticon/calculator ."
              }
          }
          stage("Docker push") {
             steps {
                sh "docker push Pteropticon/calculator"
                }
          }
    }
}