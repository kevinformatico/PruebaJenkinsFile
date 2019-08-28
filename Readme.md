# Venta Empresa testing Junit-Cucumber-Selenium

### __IMPORTANTE__: Se debe configurar la siguiente variable de entorno para asegurar la correcta ejecución del proyecto
``JAVA_TOOL_OPTIONS = -Dfile.encoding=UTF8`` 


## Ejecución de pruebas

 - __No impacto__: Se debe ejecutar el siguiente comando ``mvn clean install -Dcucumber.options='--tags @NoImpactoR29'``
 - __Historia de usuario__: Se debe ejecutar el siguiente comando ``mvn clean install -Dcucumber.options='--tags @CV-1519'``, 
    donde el ``@CV-1519`` indica la historia en jira.
 - __Ejecución todas las historias de usuarios__: ``mvn clean install -Dcucumber.options='--tags ~@NoImpactoR29'`` la ``~``indica que se debe ignorar el tag


### Environment Setup

- Install [JDK 8](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html) 
- Install [Maven](https://maven.apache.org/install.html) 
- Install [Chrome](https://www.google.com/intl/es-419/chrome/) / [firefox](https://www.mozilla.org/es-CL/firefox/new/)

### Running the tests
```
$ mvn clean install
```

By default the browser is Chrome, also you can add arguments `-Dbrowser=firefox` to run test on firefox

 ```
 $ mvn clean install -Dbrowser=firefox
 ```


### Resources
##### [Cucumber Docs](https://cucumber.io/docs)

##### [Cucumber School Lessons](https://cucumber.io/school#lessons)

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)

##### [Junit Documentation](http://junit.org/javadoc/latest/index.html)

##### [Java Documentation](https://docs.oracle.com/javase/7/docs/api/)

##### [Stack Overflow](http://stackoverflow.com/)
* A great resource to search for issues not explicitly covered by documentation.