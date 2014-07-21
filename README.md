Sample Java application featuring [Apache Olingo](http://olingo.apache.org) 4.0 client library showing OAuth2 support from [Azure Active Directory](http://azure.microsoft.com/en-us/services/active-directory/).

### How to test

1. `git clone https://github.com/Tirasa/olingoClientOAuth2Sample.git`
1. `cd olingoClientOAuth2Sample`
1. fill the required information under `src/main/resources/oauth2.properties` (see [this blog post](https://ahmetalpbalkan.com/blog/azure-rest-api-with-oauth2/) for more information)
1. customize the actual OData calls in `src/main/java/net/tirasa/olingooauth2/Main` (sample for accessing Office 365 SDK is currently reported)
1. build and run: `mvn clean package exec:exec`
