@param gg.jte.Content headerRightContent
@param gg.jte.Content pageContent

@template.appLayout(content = @`
  <div class="navbar bg-base-100 shadow-sm px-8">
    <div class="flex-1">
      <p class="font-sans text-2xl">
        <span class="font-mono font-black text-orange-700">URL</span>
        <span class="font-light">Shortener</span>
        <span class="font-thin text-sm">by Spring Boot 4</span>
      </p>
    </div>
    <div class="flex-none">
      ${headerRightContent}
    </div>
  </div>

  <main>
    ${pageContent}
  </main>
`)
