package com.provectus.kafka.ui.utilities.qaseUtils;

import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.provectus.kafka.ui.settings.BaseSource.SUITE;
import static org.apache.commons.lang3.BooleanUtils.FALSE;
import static org.apache.commons.lang3.BooleanUtils.TRUE;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
public class QaseExtension {

    public static void setupTestRun() {
        String qaseApiToken = System.getProperty("QASEIO_API_TOKEN");
        if (isEmpty(qaseApiToken)) {
            log.warn("QASEIO_API_TOKEN system property wasn't set. Support for Qase will be disabled.");
            System.setProperty("QASE_ENABLE", FALSE);
        } else {
            log.warn("QASEIO_API_TOKEN was set. Find this run at https://app.qase.io/run/KAFKAUI.");
            System.setProperty("QASE_ENABLE", TRUE);
            System.setProperty("QASE_PROJECT_CODE", "KAFKAUI");
            System.setProperty("QASE_API_TOKEN", qaseApiToken);
            System.setProperty("QASE_USE_BULK", TRUE);
            System.setProperty("QASE_RUN_NAME", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    .format(OffsetDateTime.now(ZoneOffset.UTC)) + ": " + "Automation " + SUITE.toUpperCase() + " suite");
        }
    }
}