function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  karate.configure('readTimeout', 60000);
   var baseUrl = "https://jsonplaceholder.typicode.com"

  if (!env) {
    env = 'dev';
  }
   karate.configure('charset', null);

  var config = {
    env: env,
    baseUrl: baseUrl,
    usersUrl : baseUrl+"/users",
    loginToken: function(authenticationResult) {
           return 'Bearer ' + authenticationResult.token;
    },
         clientToken: function(authenticationResult) {
           return 'Bearer ' + authenticationResult.token;
         },
         getCurrentDate: function(date_format) {
               var SimpleDateFormat = Java.type('java.text.SimpleDateFormat');
               var sdf = new SimpleDateFormat(date_format);
               var date = new java.util.Date();
               return sdf.format(date);
             },
         getRandomInteger: function(min,max){ return Math.floor(Math.random() * (max - min) + min)}
  };
  return config;
}