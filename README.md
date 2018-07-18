# ProjectCatalogueMovieS1
Aplikasi katalog movie untuk submission pertama beasiswa MADE (Menjadi Android Developer Expert) dari Dicoding https://www.dicoding.com/academies/14/ . Thanks to Google yang telah memberikan beasiswa
untuk dapat mengikuti kelas ini.

<pre>
<img src="Screenshot/Screenshot_2018-06-22-12-52-21.png" width="250" height="444">         <img src="Screenshot/Screenshot_2018-06-22-12-52-40.png" width="250" height="444">
</pre>

### Persyaratan aplikasi

* [x] Halaman untuk mencari film.
* [x] Halaman detail untuk menampilkan detail fim yang telah dipilih pada halaman list film.
* [x] Tampilan poster dari film.

### Petunjuk menjalankan source code aplikasi

Untuk menjalankan source code aplikasi ini, anda perlu registrasi API KEY dari www.themoviedb.org
kemudian memasukkan API KEY yang telah didapat ke dalam file ***utils/UtilsConstant.java***

```
public final static String API_KEY = "Paste API KEY anda disini";
```

Kemudian hapus baris berikut pada file ***build.gradle***

```
buildConfigField 'String', "ApiKey", Catalogue_Movie_ApiKey
```

## Author

* **R Rifa Fauzi Komara**

Jangan lupa untuk follow dan ★
