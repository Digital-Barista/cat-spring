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
    var entry = fsa.State.create({ position: {x: 150, y: 50}, label: "Entry" });
    var message = fsa.State.create({ position: {x: 50, y: 150}, label: "Message" });
    var coupon = fsa.State.create({ position: {x: 300, y: 150}, label: "Coupon" });
    var message2 = fsa.State.create({ position: {x: 150, y: 250}, label: "Message" });
    var coupon2 = fsa.State.create({ position: {x: 450, y: 250}, label: "Coupon" });
    
    var all = [entry, message, coupon, message2, coupon2];
    
    var mArrow = Joint.dia.fsa.arrow;
    mArrow.label = 'Immediate';
    entry.joint(message, mArrow).registerForever(all);
    entry.joint(coupon, mArrow).registerForever(all);
    coupon.joint(message2, mArrow).registerForever(all);
    coupon.joint(coupon2, mArrow).registerForever(all);
  }
});
dbi.build = new dbi.Build();