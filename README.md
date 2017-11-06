# Grace
>0. 引入Grace
  ```java
  dependencies {
    compile 'com.github.hiviiup:grace:2.1.0'
	}
  ```
>1. 初始化Grace
  创建Application,在oncreate方法中调用Grace.init();
  
>2. 修改style.xml
  将parent 修改成 noactionbar

   <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar"> 
        <item name="colorPrimary">@color/colorPrimary</item> 
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item> 
        <item name="colorAccent">@color/colorAccent</item> 
	</style>
 
    


# V2.1.0 更新功能：
1. 新增获取手机图片列表功能
2. 新增调用系统相机功能
3. 新增图片Uri转Path工具
