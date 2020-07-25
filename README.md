# Android Utilitools - Security

[![](https://jitpack.io/v/bhapps/UtilitoolsSecurity.svg)](https://jitpack.io/#bhapps/UtilitoolsSecurity)

Android Utilities Security has functions for fast prototyping and development of Android Applications that require features and functionality to build upon.
* Still Under Development, docs, updates to be on going

# To be Done

* Add doc links to methods etc

# Built Using

* Java
* Kotlin
* Xml

# How to Use

* Add the JitPack repository to your build file 

<pre>
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
</pre>

* Add the dependency

<pre>
dependencies {
   implementation 'com.github.bhapps:UtilitoolsSecurity:0.0.1-alpha'
}
</pre>

* To return an encrypted string value, pass your string to encrypt and use the function:

<pre>
bhapps.utilitools.security.java.security.encryption.EncryptDecrypt.getEncryptedStringFromString(string_to_encrypt)
</pre>

* To return an decrypted string value, pass your encrypted string and use the function:

<pre>
bhapps.utilitools.security.java.security.encryption.EncryptDecrypt.getEncryptedStringFromString(encrypted_string_to_decrypt)
</pre>

# Functions

<pre>
    /*
     *
     * getEncryptedStringFromString(string)
     * get encrypted string from passed string value
     * bhapps.utilitools.security.java.security.encryption.EncryptDecrypt.getEncryptedStringFromString(string)
     * returns String
     *
    */
</pre>

<pre>
    /*
     *
     * getDecryptedStringFromString(string)
     * get decrypted string from passed string value
     * bhapps.utilitools.security.java.security.encryption.EncryptDecrypt.getDecryptedStringFromString(string)
     * returns String
     *
    */
</pre>

# Libraries

* AndroidX
* Kotlin

# Licences

* The Unlicense

<pre>

This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org>

</pre>

