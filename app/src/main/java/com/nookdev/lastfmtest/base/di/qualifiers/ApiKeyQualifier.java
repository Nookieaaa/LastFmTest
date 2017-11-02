package com.nookdev.lastfmtest.base.di.qualifiers;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface ApiKeyQualifier {
}
