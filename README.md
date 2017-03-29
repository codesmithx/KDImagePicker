## KDImagePicker
A modified simple library to select images from the gallery and camera with provision for use in multiple apps with varying file providers

## Screenshot

<img src="https://github.com/codesmithx/KDImagePicker/blob/master/sample_image.png" height="460" width="284"/>

Add this to your project's `build.gradle`

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

And add this to your module's `build.gradle` 

```groovy
dependencies {
	compile 'com.github.codesmithx:KDImagePicker:x.y.z'	
}
```

change `x.y.z` to version in the [release page](https://github.com/codesmithx/KDImagePicker)

## Usage

For full example, please refer to `sample`

### Start image picker activity
- Complete options

```java
KDImagePicker.create(this)
	.returnAfterFirst(true) // set whether pick or camera action should return immediate result or not. For pick image only work on single mode
	.folderMode(true) // folder mode (false by default)
	.folderTitle("Folder") // folder selection title
	.imageTitle("Tap to select") // image selection title
	.single() // single mode
	.multi() // multi mode (default mode)
	.limit(10) // max images can be selected (99 by default)
	.showCamera(true) // show camera or not (true by default)
	.origin(images) // original selected images, used in multi mode
	.start(REQUEST_CODE_PICKER); // start image picker activity with request code
```                
 
### Receive result

- Using helper 

```java
@Override
if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
    ArrayList<Picker_Image> images = (ArrayList<Picker_Image>) KDImagePicker.getImages(data);
}
```
- via Intent

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
        ArrayList<Picker_Image> images = data.getParcelableArrayListExtra(Activity_ImagePicker.INTENT_EXTRA_SELECTED_IMAGES);
        // do your logic ....
    }
}
```

- Receiving Result

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == RC_REQUEST_CAMERA && resultCode == RESULT_OK && data != null) {
        cameraModule.getImage(context, data, new OnImageReadyListener() {
            @Override
            public void onImageReady(List<Picker_Image> images) {
	    	// do what you want to do with the image ...
	    	// it's either List<Picker_Image> with one item or null (still need improvement)
            }
        });
    }
}
```
- Example on how to override file Provider
```java
 <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true"
            tools:replace="android:authorities">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/my_provider_paths"
                tools:replace="android:resource" />
        </provider>
```


##Modification License
```
Copyright (c) 2017 Kiss Devs

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

##Original License
[The Original Image Picker](https://github.com/esafirm/android-image-picker/)

