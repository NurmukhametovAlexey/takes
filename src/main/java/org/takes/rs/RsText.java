/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2022 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.rs;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.takes.Response;

/**
 * Plain text response decorator.
 *
 * <p>The class is immutable and thread-safe.
 *
 * @since 0.1
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class RsText extends RsWrap {

    /**
     * Ctor.
     * @since 0.10
     */
    public RsText() {
        this("");
    }

    /**
     * Ctor.
     * @param body Plain text body
     */
    public RsText(final CharSequence body) {
        this(new RsWithStatus(HttpURLConnection.HTTP_OK), body);
    }

    /**
     * Ctor.
     * @param body Plain text body
     */
    public RsText(final byte[] body) {
        this(new RsWithStatus(HttpURLConnection.HTTP_OK), body);
    }

    /**
     * Ctor.
     * @param body Plain text body
     */
    public RsText(final InputStream body) {
        this(new RsWithStatus(HttpURLConnection.HTTP_OK), body);
    }

    /**
     * Ctor.
     * @param url URL with body
     * @since 0.10
     */
    public RsText(final URL url) {
        this(new RsWithStatus(HttpURLConnection.HTTP_OK), url);
    }

    /**
     * Ctor.
     * @param res Original response
     * @param body HTML body
     */
    public RsText(final Response res, final CharSequence body) {
        this(new RsWithBody(res, body));
    }

    /**
     * Ctor.
     * @param res Original response
     * @param body HTML body
     */
    public RsText(final Response res, final byte[] body) {
        this(new RsWithBody(res, body));
    }

    /**
     * Ctor.
     * @param res Original response
     * @param body HTML body
     */
    public RsText(final Response res, final InputStream body) {
        this(new RsWithBody(res, new RsBody.TempFile(new RsBody.Stream(body))));
    }

    /**
     * Ctor.
     * @param res Original response
     * @param url URL with body
     */
    public RsText(final Response res, final URL url) {
        this(new RsWithBody(res, url));
    }

    /**
     * Ctor.
     * @param res Original response
     * @since 0.10
     */
    public RsText(final Response res) {
        super(new RsWithType(res, "text/plain"));
    }

}
