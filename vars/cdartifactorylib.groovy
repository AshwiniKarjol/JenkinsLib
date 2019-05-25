def call(String serverid,String source,String destination)

   {
   rtDownload (

    serverId: "${serverid}",

    spec:

        """{

          "files": [

            {

              "pattern": "${source}/${env.BUILD_NUMBER}/",

              "target": "${destination}"

            }

         ]

        }"""

)
   }
