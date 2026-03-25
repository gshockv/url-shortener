@template.layouts.pageLayout(
  headerRightContent = @`
    <a href="/dashboard">Links Dashboard</a>
  `,
  pageContent = @`

  <div class="hero min-h-screen w-full items-start pt-28">
    <div class="card w-250 shadow-sm bg-base-300/70">
      <div class="card-body py-12">
        <p class="pb-4">Enter URL here which you want to short. This shorten link will be handled by this app</p>

        <form
          name="shortLinkForm"
          hx-post="/shortLink"
          hx-target="#shortenedLinkContainer"
          hx-swap="innerHTML">

          <div class="flex w-full">
            <input type="text" class="input flex-1 mr-2" name="linkToShort" placeholder="Enter URL..." />
            <button class="btn btn-primary flex-none" type="submit">Short it</button>
          </div>

          <div id="shortenedLinkContainer" class="mt-4">

          </div>

        </form>
      </div>
    </div>
  </div>

`)