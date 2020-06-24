# ImageSubRedditViewe

# Features of this library are:-

1. able to asynchronously load the image onto the ImageView.
2. able to load the images faster by caching it in memory.
3. able to load the images faster by using disk-level caching.


# How to use this in your app?

```java
        ImageLoader.get(context)
                .loadUrl(url)
                .target(imageView)
                .execute();
```
   
