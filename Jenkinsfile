@Library('my_shared_library')_
node(label:'master') {
   def mvnHome
   stage('Initialization') { 
      // Get some code from a GitHub repository
     // git 'https://github.com/AshwiniKarjol/MVC.git'
     git 'https://github.com/AshwiniKarjol/spring3-mvc-maven-xml-hello-world'
     
     
   }


stage('Build'){
   withSonarQubeEnv('SonarQube1') {
   mvnHome = tool 'Maven'
   def var = '-Dsonar.host.url=http://104.211.213.175:9000 -Dsonar.login=07062dc8d19fea91d32cf9ca1d0dfdeb4633c747'
       sh "'${mvnHome}/bin/mvn' ${var} clean install sonar:sonar"
   } 
}
   
 
   //SOnar Quality gate 
   stage('Sonar Quality gate')
   {  
  sonarqube "SonarQube1"
   }
   
   //Building and publishing image to hub
   stage('Docker image build')
   {
      dockerbuild "https://registry.hub.docker.com","DockerAuth1","seconddocker/image"     
   }
   
   //moving the artifacts to jfrog
   stage('Deploy artifacts')
   {
    artifactorylib "Artifactory1","./target/*.war","libs-snapshot-repo"
   }
   
     stage('Notify')
   {
   emailext (
      subject: "Successful : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>BCI job is successful: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
   }
   
}
