# task-jaggrat

I have used following tech-stack to develop this application

- [x] MVVM: Used MVVM with ViewModel and LiveData to maintain updated state of UI during configuration change.
- [x] Kotlin: Used kotlin for business, view, and test cases 100%. 
- [x] RxJava : To fetch the response from API. 
- [x] Retrofit : Called API with query params.
- [x] Dagger : Dependency injector. 
- [x] Mockito : Tested ViewModel since it is handling local logic of blocking/following. Used mockito to mock the repository.The code coverage for ViewModel is 85%.
- [x] Espresso : To test UI. 
- [x] Offline : A toast message is shown to user when there is no internet. As soon as there is a internet connection application shows the result to user. 

Note: If I had more time I could have done the better job in UI.   

Test case report: 

Espresso - 
![alt text](https://github.com/jsin67/task-jaggrat/blob/master/Screen%20Shot%202019-08-08%20at%203.38.13%20PM.png)

Mockito - 

![alt text](https://github.com/jsin67/task-jaggrat/blob/master/Screen%20Shot%202019-08-08%20at%203.38.46%20PM.png)
