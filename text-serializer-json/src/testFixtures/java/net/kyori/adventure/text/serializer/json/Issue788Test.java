/*
 * This file is part of adventure, licensed under the MIT License.
 *
 * Copyright (c) 2017-2023 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.adventure.text.serializer.json;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.junit.jupiter.api.Test;

// https://github.com/KyoriPowered/adventure/issues/788
final class Issue788Test extends SerializerTest {
  @Test
  void test() {
    this.testObject(
      Component.text()
        .append(Component.text("PREPEND>", TextColor.color(0xFF00FF)))
        .append(Component.text("/sign test"))
        .append(Component.text("<APPEND", TextColor.color(0xFF00FF)))
        .build(),
      json -> {
        json.addProperty(JSONComponentConstants.TEXT, "");
        json.add(JSONComponentConstants.EXTRA, array(extra -> {
          extra.add(object(object -> {
            object.addProperty(JSONComponentConstants.COLOR, "#FF00FF");
            object.addProperty(JSONComponentConstants.TEXT, "PREPEND>");
          }));
          extra.add("/sign test");
          extra.add(object(object -> {
            object.addProperty(JSONComponentConstants.COLOR, "#FF00FF");
            object.addProperty(JSONComponentConstants.TEXT, "<APPEND");
          }));
        }));
      }
    );
  }
}
