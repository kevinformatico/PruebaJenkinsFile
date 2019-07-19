pipeline {
  agent { dockerfile true }
  stages {
    stage('No impacto') {
      steps {
        sh 'mvn clean install -Dheadless=true'
      }
    }
  }
  post {
    always {
      archiveArtifacts(artifacts: 'target/', fingerprint: true)
      junit 'target/cucumber.xml'
      publishTestResults  serverAddress: 'http://jira.bch.bancodechile.cl:8080',
                          projectKey: 'VTAPYME',
                          filePath:'target/cucumber-report/cucumber.json',
                          format: 'Cucumber',
                          autoCreateTestCases: false
      cucumber fileIncludePattern: 'target/cucumber-report/cucumber.json', sortingMethod:'ALPHABETICAL'
      publishHTML([allowMissing: true, alwaysLinkToLastBuild: false,
      keepAll: true, reportDir: 'target/generated-report', reportFiles: 'index.html', reportName: 'Integration Test', reportTitles: ''])
    }
  }
}
