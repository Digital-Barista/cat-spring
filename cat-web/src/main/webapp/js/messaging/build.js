dbi.Build = Class.extend({
  paper: undefined,
  
  init: function(){
    
  },
  
  setup: function(){
    this.paper = Joint.paper("workspace", 920, 500);
    this.buildCampaign();
  },
  
  buildCampaign: function(){
    var fsa = Joint.dia.fsa;
    var entry = fsa.State.create({ position: {x: 300, y: 50}, label: "Entry" });
    var message = fsa.State.create({ position: {x: 50, y: 150}, label: "Message" });
    var coupon = fsa.State.create({ position: {x: 600, y: 150}, label: "Coupon" });
    
    var all = [entry, message, coupon];
    
    var mArrow = Joint.dia.fsa.arrow;
    entry.joint(message, (mArrow.label = 'Immediate', mArrow)).registerForever(all);
    entry.joint(coupon, fsa.arrow).registerForever(all);
  }
});
dbi.build = new dbi.Build();