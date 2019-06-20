pipeline {
  agent any
  stages {
    stage('Clean Work Space'){
        steps {
            sh 'mvn clean'
            }
         }
    stage('Integration Test') {
      steps {
        sh 'mvn install -Dheadless=true'
      }
    }
  }
  post {
    always {
      archiveArtifacts(artifacts: 'target/', fingerprint: true)
      junit 'target/cucumber.xml'
      publishTestResults  serverAddress: 'http://jira.bch.bancodechile.cl:8080',
                          projectKey: 'CDNVIS',
                          filePath:'target/cucumber-report/cucumber.json',
                          format: 'Cucumber',
                          autoCreateTestCases: false
    }
  }
}
