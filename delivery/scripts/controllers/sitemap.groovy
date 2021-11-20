import org.craftercms.search.elasticsearch.ElasticsearchWrapper
import org.craftercms.engine.service.context.SiteContext

def pageItems = queryPages(params.route, elasticsearch)

response.setContentType("application/xml")
def xml = new groovy.xml.MarkupBuilder(response.getWriter())

def xmlHelper = new groovy.xml.MarkupBuilderHelper(xml)
xmlHelper.xmlDeclaration(version:"1.0", encoding:"UTF-8")

xml.urlset(xmlns:"http://www.sitemaps.org/schemas/sitemap/0.9") {
    pageItems.each { v ->
        if(!v.localId.contains(".level.xml")) {
            url {
                SiteContext context = SiteContext.current 
                url =  urlTransformationService.transform("storeUrlToRenderUrl", v.localId)
                loc(url)
                lastmod(v.lastModifiedDate_dt)  
                changefreq("weekly")
                priority(0.8000)
            }
        }
    }
}

response.flushBuffer()

return null

/**
 * query pages for a given route
 */
def queryPages(route, elasticsearch) {
    def routeFolder = params.route ? params.route : "/"
    def path = '/site/website'+routeFolder+'*'
    def results = elasticsearch.search( 
        [ query: 
            [ bool: 
                [ filter: 
                    [ wildcard: [ 'localId': path ] ] 
                    ] 
                ] 
        ]).hits.hits*.sourceAsMap  
    return results  
}