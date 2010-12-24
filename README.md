lein-antlr
==========

**lein-antlr** is a [Leiningen](https://github.com/technomancy/leiningen) plugin for generating source
code from one or more [ANTLR](http://www.antlr.org) grammars in a Leiningen project. It has roughly
the same functionality as the Maven ANTLR plugin, and is intended to allow developers to integrate
ANTLR-generated source code into a Clojure project without resorting to Maven or some other manual process.

Download It
-----------

The lein-antlr plugin is available for download at [Clojars](http://clojars.org/). The group and
artifact IDs are both 'lein-antlr'.

Or better, yet, just add it to your project's dev-dependencies and let Leiningen do the work for you:

    :dev-dependencies [[lein-antlr "0.1.0"]]

Usage
-----

The lein-antlr plugin can be called from the command-line as follows:

    % lein antlr

The plugin is configured in your <tt>project.clj</tt> as follows:

    (defproject my-project
      ...
      :antlr-src-dir "src/antlr"
      :antlr-dest-dir "gen-src"
      :antlr-options {:verbose true
                      ... }
    )

The plugin will scan the source directory specified by <tt>:antlr-src-dir</tt> and its subdirectories for all
ANTLR grammar files (i.e. those files whose names end in '.g') and compile them, placing the generated
source code into the destination directory specified by <tt>:antlr-dest-dir</tt>. Grammar files located in
subdirectories of the source directory will have their generated code placed into corresponding subdirectories
in the destination directory.

The default values for <tt>:antlr-src-dir</tt> and <tt>:antlr-dest-dir</tt> are 'src/antlr' and 'gen-src' respectively.

Options
-------

The behavior of the ANTLR tool is configured using the <tt>:antlr-options</tt> entry in your project
description. This entry should be a map of keyword-value pairs as follows:

<table border="1" cellspacing="3" cellpadding="5">
 <tr>
  <th>Option</th>
  <th>Type</th>
  <th>Default Value</th>
  <th>Description</th>
 </tr>
 <tr>
  <td><tt>:conversion-timeout</tt></td>
  <td>integer</td>
  <td>10000</td>
  <td>The timeout that ANTLR will wait before giving up when analyzing a decision in the grammar.</td>
 </tr>
 <tr>
  <td><tt>:debug</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Generate code in debug mode (i.e. starts up and waits for a debug connection on a TCP port).
Useful for interacting with ANTLRWorks; be sure to disable for production code.</td>
 </tr>
 <tr>
  <td><tt>:trace</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Generate code that will log rule entry and exit points to stdout when executed.</td>
 </tr>
 <tr>
  <td><tt>:profile</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Generate code that will collect and report profiling information when executed.</td>
 </tr>
 <tr>
  <td><tt>:report</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Report information about the grammars as they are processed.</td>
 </tr>
 <tr>
  <td><tt>:verbose</tt></td>
  <td>boolean</td>
  <td>true</td>
  <td>Put the ANTLR tool into verbose mode; will not affect the generated code.</td>
 </tr>
 <tr>
  <td><tt>:print-grammar</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Print a version of the grammar with actions removed as it is processed.</td>
 </tr>
 <tr>
  <td><tt>:message-format</tt></td>
  <td>String</td>
  <td>"antlr"</td>
  <td>Determines the format to use for warning and error messages returned by ANTLR.
Should be one of "antlr", "gnu", or "vs2005".</td>
 </tr>
 <tr>
  <td><tt>:max-switch-case-labels</tt></td>
  <td>integer</td>
  <td>300</td>
  <td>The maximum number of rule alternatives that ANTLR will condense into a case statement.</td>
 </tr>
 <tr>
  <td><tt>:dfa</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Generate a description of the DFA for each decision in the grammar as it is processed.</td>
 </tr>
 <tr>
  <td><tt>:nfa</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>Print a description of the NFA for each rule as it is analyzed.</td>
 </tr>
</table>

Cleaning Up
-----------

The plugin can be configured to clean the generated source directory as part of the Leiningen 'clean'
task, but this must be manually set up by adding the <tt>leiningen.antlr</tt> namespace to the project
hooks, like so:

    (defproject my-project
      ...
      :hooks [leiningen.antlr]
      ...
    )

---

License & Copyright
-------------------

Copyright (c) 2010 Revelytix, Inc.

The lein-antlr project is distrubuted under the [Apache Software License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
