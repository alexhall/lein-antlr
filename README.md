lein-antlr
==========

**lein-antlr** is a [Leiningen 2](https://github.com/technomancy/leiningen) plugin for generating source
code from one or more [ANTLR 4](http://www.antlr.org) grammars in a Leiningen project. It has roughly
the same functionality as the Maven ANTLR plugin, and is intended to allow developers to integrate
ANTLR-generated source code into a Clojure project without resorting to Maven or some other manual process.

To use <tt>lein-antlr</tt> in your project, simply add it to <tt>:plugins</tt> in your <tt>project.clj</tt>:

    :plugins [[lein-antlr "0.2.2-SNAPSHOT"]]
	
Leiningen 1.x users can use the old 0.1.0 version of <tt>lein-antlr</tt>.

Usage
-----

The lein-antlr plugin can be called from the command-line as follows:

    % lein antlr

The plugin is configured in your <tt>project.clj</tt> as follows:

    (defproject my-project
      ...
      :antlr-src-dir "src/antlr"
      :antlr-dest-dir "gen-src"
      :antlr-options {:Werror true
                      ... }
    )

The plugin will scan the source directory specified by <tt>:antlr-src-dir</tt> and its subdirectories for all
ANTLR grammar files (i.e. those files whose names end in '.g' or '.g4') and compile them, placing the generated
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
  <td><tt>:atn</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>generate rule augmented transition network diagrams</td>
 </tr>
 <tr>
  <td><tt>:encoding</tt></td>
  <td>String</td>
  <td></td>
  <td>specify grammar file encoding; e.g., euc-jp</td>
 </tr>
 <tr>
  <td><tt>:message-format</tt></td>
  <td>String</td>
  <td></td>
  <td>specify output style for messages in antlr, gnu, vs2005</td>
 </tr>
 <tr>
  <td><tt>:long-messages</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>show exception details when available for errors and warnings</td>
 </tr>
 <tr>
  <td><tt>:listener</tt></td>
  <td>boolean</td>
  <td>true</td>
  <td>generate parse tree listener (default)</td>
 </tr>
 <tr>
  <td><tt>:no-listener</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>don't generate parse tree listener</td>
 </tr>
 <tr>
  <td><tt>:visitor</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>generate parse tree visitor</td>
 </tr>
 <tr>
  <td><tt>:no-visitor</tt></td>
  <td>boolean</td>
  <td>true</td>
  <td>don't generate parse tree visitor (default)</td>
 </tr>
 <tr>
  <td><tt>:package</tt></td>
  <td>String</td>
  <td>as specified in grammar-files</td>
  <td>specify a package/namespace for the generated code</td>
 </tr>
 <tr>
  <td><tt>:depend</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>generate file dependencies</td>
 </tr>
 <tr>
  <td><tt>:Werror</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>treat warnings as errors</td>
 </tr>
 <tr>
  <td><tt>:Xlog</tt></td>
  <td>boolean</td>
  <td>false</td>
  <td>dump lots of logging info to antlr-timestamp.log</td>
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
