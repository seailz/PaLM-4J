package com.seailz.palm4j.api.entities.filter;

/**
 * A list of reasons why content may have been blocked.
 *
 * @author Seailz
 * @version 1.0.0
 */
public enum BlockedReason {
    // A block reason was not specified.
    BLOCKED_REASON_UNSPECIFIED,
    // Content was blocked by safety settings.
    SAFETY,
    // content was blocked, but the reason is uncategorized.
    OTHER
}
