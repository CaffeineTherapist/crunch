/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.crunch;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Container class that includes optional information about a {@code parallelDo} operation
 * applied to a {@code PCollection}. Primarily used within the Crunch framework
 * itself for certain types of advanced processing operations, such as in-memory joins
 * that require reading a file from the filesystem into a {@code DoFn}.
 */
public class ParallelDoOptions {
  private final Set<SourceTarget<?>> sourceTargets;
  
  private ParallelDoOptions(Set<SourceTarget<?>> sourceTargets) {
    this.sourceTargets = sourceTargets;
  }
  
  public Set<SourceTarget<?>> getSourceTargets() {
    return sourceTargets;
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  public static class Builder {
    private Set<SourceTarget<?>> sourceTargets;
    
    public Builder() {
      this.sourceTargets = Sets.newHashSet();
    }
    
    public Builder sourceTargets(SourceTarget<?>... sourceTargets) {
      Collections.addAll(this.sourceTargets, sourceTargets);
      return this;
    }

    public Builder sourceTargets(Collection<SourceTarget<?>> sourceTargets) {
      this.sourceTargets.addAll(sourceTargets);
      return this;
    }

    public ParallelDoOptions build() {
      return new ParallelDoOptions(sourceTargets);
    }
  }
}
