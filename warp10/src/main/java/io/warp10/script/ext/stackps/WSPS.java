//
//   Copyright 2020-2022  SenX S.A.S.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

package io.warp10.script.ext.stackps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.warp10.script.NamedWarpScriptFunction;
import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;
import io.warp10.script.WarpScriptStackFunction;
import io.warp10.script.WarpScriptStackRegistry;
import io.warp10.warp.sdk.Capabilities;

public class WSPS extends NamedWarpScriptFunction implements WarpScriptStackFunction {

  public WSPS(String name) {
    super(name);
  }

  @Override
  public Object apply(WarpScriptStack stack) throws WarpScriptException {

    if (null == Capabilities.get(stack, Capabilities.STACKPS_CAPABILITY)) {
      throw new WarpScriptException(getName() + " missing capability.");
    }

    List<Object> results = new ArrayList<Object>();

    for (WarpScriptStack stck: WarpScriptStackRegistry.stacks()) {
      Map<Object,Object> result = WSINFO.getInfos(stck);

      results.add(result);
    }

    stack.push(results);

    return stack;
  }
}
