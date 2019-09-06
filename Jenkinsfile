pipeline {

  agent any

  stages {

      stage('Limpia el Proyecto') {
          steps {
              bat mvn clean
          }
      }

      stage('Ejecutpo las pruebas') {
          steps {
             bat mvn install
          }
      }
  }
}

