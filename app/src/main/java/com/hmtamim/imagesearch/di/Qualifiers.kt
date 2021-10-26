package com.hmtamim.imagesearch.di

import javax.inject.Qualifier

interface Qualifiers {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class DatabaseName
}