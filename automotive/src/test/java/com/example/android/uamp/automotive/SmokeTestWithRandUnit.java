package com.example.android.uamp.automotive;

import com.williamfzc.randunit.RandUnitAndroid;
import com.williamfzc.randunit.cases.RUJUnit4Case;
import com.williamfzc.randunit.models.StatementModel;

import org.junit.runner.RunWith;
import org.robolectric.ParameterizedRobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collection;
import java.util.HashSet;

@Config(sdk = {28})
@RunWith(ParameterizedRobolectricTestRunner.class)
public class SmokeTestWithRandUnit extends RUJUnit4Case {
    public SmokeTestWithRandUnit(StatementModel sm) {
        super(sm);
    }

    static {
        testEnv.getEnvConfig().getMockConfig().setKtFirst(true);
    }

    public static Collection<StatementModel> cache = new HashSet<>();

    @ParameterizedRobolectricTestRunner.Parameters
    public static Collection<StatementModel> data() {
        if (cache.isEmpty()) {
            scannerConfig.setIncludeFilter(new HashSet<String>() {{
                add("com.example.android");
            }});
            scannerConfig.setStatementLimit(20000);
            cache = RandUnitAndroid.INSTANCE.collectStatementsWithPackage("com.example.android", scannerConfig);
        }
        return cache;
    }
}
