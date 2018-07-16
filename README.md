
# MarqueeTextView
An android library of custom view for implementing marquee text based on RecyclerView

## Features
1. Based on RecyclerView.
2. Smooth, the text is scrolled by RecyclerView.
3. Unlimited text size, since our approach will split text into multiple parts.

## Setup
1.  In root build.gradle:
  ```
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
  ````

2.  In target module build.gradle
  ```
  dependencies {
    compile 'com.github.joneslin:MarqueeTextView:v1.0'
  }
  ```

## Usage

1. Initialize and automatically start
  ```java
  MarqueeTextView marqueeTextView = (MarqueeTextView) findViewById(R.id.marquee_text_view);
  marqueeTextView.initialize(text, color, isBold, isTwinkling);
  ```

2. Stop
  ```java
  marqueeTextView.stop();
  ```

