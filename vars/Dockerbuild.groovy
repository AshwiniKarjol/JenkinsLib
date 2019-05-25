def call (String deockerHub, String authcode, String imagePath)
{

docker.withRegistry("${deockerHub}", "${authcode}") {

       def customImage = docker.build("${imagePath}:${BUILD_NUMBER}")

       customImage.push()
}
