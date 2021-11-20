# Google XML Sitemap Plugin for Crafter CMS

Add Google XML Sitemap support to your site

# Installation

Install the plugin via Studio's Plugin Management UI under Site Tools > Plugin Management.

# Usage

Add the following config to your site's Engine's rewrite rules (/config/engine/site-config.xml)
```
    <rule match-type="wildcard">
        <from>^/sitemap.xml</from>
        <to type="redirect">/sitemap/$1</to>
    </rule>

```
