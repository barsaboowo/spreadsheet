package com.dbs.test;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by b on 11/2/18.
 */
public class TestConstants {
    public static final List<String> LINES = ImmutableList.of(
            "1.00000,2.00000,3.00000,4.00000,5.00000",
            "1.00000,2.00000,3.00000,4.00000,5.00000"
    );

    public static final List<String> SAMPLE_RESULT = ImmutableList.of(
            "2.00000,4.00000,1.00000,6.00000",
            "18.00000,0.00000,0.00000,3.00000"
    );

    public static final List<String> SAMPLE_LINES = ImmutableList.of(
            "2,4,1,=A0+A1*A2",
            "=A3*(A0+1),=B2,0,=A0+1"
    );
}
