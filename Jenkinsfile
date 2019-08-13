pipeline {
  agent {
    docker {
      image '152.139.146.94:5000/bch-maven-chrome:1.1.0'
      args '--add-host="portalcomercial.qa.labchile.cl:200.14.169.120"'
    }
  }
  stages {
    stage('No impacto'){
      steps {
        sh 'mvn clean install -Dheadless=true -Ddocker=true'
      }
    }
  }
  post {
    always {
      archiveArtifacts(artifacts: 'target/', fingerprint: true)
      junit 'target/cucumber.xml'
      publishTestResults(serverAddress: 'http://jira.bch.bancodechile.cl:8080', projectKey: 'VTAPYME', filePath: 'target/cucumber-report/cucumber.json', format: 'Cucumber', autoCreateTestCases: true)
    }
  } 
}
