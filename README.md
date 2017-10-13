# Grace
>0. 引入Grace

  ```java
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  ```
  
  ```java
  dependencies {
	        compile 'com.github.hiviiup:Grace:1.0'
	}
  ```
  
>1. 修改style.xml
  将parent 修改成 noactionbar
   ```html
   <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar"> 
        <item name="colorPrimary">@color/colorPrimary</item> 
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item> 
        <item name="colorAccent">@color/colorAccent</item> 
    </style> 
    ```
    
>2. 初始化Grace
  创建Application,在oncreate方法中调用Grace.init();
