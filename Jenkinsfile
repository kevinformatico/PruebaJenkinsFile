pipeline {
  agent {
      docker {
        image 'markhobson/maven-chrome'
      }

    }
  stages {
    stage('No impacto') {
      steps {
        sh 'mvn install -Dcucumber.options='--tags @NoImpactoR29' -Dheadless=true'
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
