/*
 * Copyright (c) Hieronimus Fredy Morgan
 */
package com.morg.mycomponent.showcase;

import android.graphics.Path;
import android.graphics.RectF;

public interface Targetable {

    Path guidePath();

    RectF boundingRect();
}
