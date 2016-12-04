import { RankerUiPage } from './app.po';

describe('ranker-ui App', function() {
  let page: RankerUiPage;

  beforeEach(() => {
    page = new RankerUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
