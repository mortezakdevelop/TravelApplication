package com.example.travelapplication.model


/*به ما ارور begin array را میداد . برای این که فایل json ما درون یه آرایه است.
 برای حل این مشکل آن را درون یه دیتا کلاس بریزیم یا فایل json خود را از حالت آرایه در بیاوریم
 */


// تمام لیست هایی که درون attraction وجود دارد را در این جا قرار می دهیم
data class AttractionResponse(
    val attractions:List<Attraction> = listOf()
)
