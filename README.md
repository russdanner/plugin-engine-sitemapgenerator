# Google XML Sitemap Plugin for CrafterCMS

Add an XML sitemap to your website for better SEO

# Installation

Install the plugin via Studio's Plugin Management UI under Site Tools > Plugin Management.

# Usage

Add the following config to your site's Engine's rewrite rules (`/config/engine/urlrewrite.xml`)
```
    <!-- Send /sitemap.xml requests to the sitemap generator's controller -->
    <rule>
        <from>^/sitemap.xml</from>
        <to type="forward">/plugins/org/rd/plugin/sitemapgenerator/sitemapgenerator/sitemap</to>
    </rule>

```

Best Practice: Make sure you are pointing to /sitemap in your `robots.txt`
```
User-agent: *
Allow: /*
Sitemap: https://YOURDOMAIN/sitemap.xml
```

# Excludes
You can exclude paths from the sitemap by putting a comma seperated list of regexes in the following tags within the `config/engine/site-config.xml`

```
  <siteMap> 
    <excludes>^.*level[\\.]xml\\$, /site/website/r/.*</excludes> 
  </siteMap> 
```

