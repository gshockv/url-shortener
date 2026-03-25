@import io.gshockv.shrtr.domain.ShortLinkPage
@import io.gshockv.shrtr.domain.ShortLink

@param ShortLinkPage<ShortLink> page

@template.layouts.pageLayout(
headerRightContent = @`
`,
pageContent = @`
  <div class="min-h-screen flex flex-col">

    <div
      id="tableContainer"
      hx-get="/links/table?page=${page.getPage()}"
      hx-trigger="load"
      hx-target="#tableContainer"
      hx-swap="innerHTML"
    >
      Loading...
    </div>

  </div>
`)
