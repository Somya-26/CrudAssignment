# CrudAssignment

3 functionalities are performed.

Login Api - /login(unauthorised)
  checks if the username and passowrd of the user are correct.

Save Content - /content/save?url=''
  Hit the url passed in request param and saves the content fetched from url as string in database.
  
Fetch Content - /content/fetch
  Fetch the content as list of strings from database for logged in user.
  
