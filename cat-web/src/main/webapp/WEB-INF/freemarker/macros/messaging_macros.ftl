<#macro showMessageFields>
  <table>
    <tr>
      <td class="label">Message Type</td>
      <td>
        <input type="radio" name="messageType" value="Message" checked /> Message
        <input type="radio" name="messageType" value="Coupon" /> Coupon
      </td>
      <td class="label">Title</td><td><input type="text" name="title" /></td>
    </tr>
    <tr class="coupon-field">
      <td class="label">Offer Code</td><td><input type="text" name="offerCode" /></td>
      <td class="label">Coupon Code</td>
      <td>
        <input type="radio" name="couponCode" checked /> Random
        <input type="radio" name="couponCode" /> Static
      </td>
    </tr>
    <tr class="coupon-field">
      <td class="label">Max Coupons</td>
      <td><input type="checkbox" name="maxCoupons" /></td>
      <td class="label">Unavailable Date</td><td><input type="text" name="unavailableDate" /></td>
    </tr>
    <tr class="coupon-field">
      <td class="label">Max Redemptions</td>
      <td><input type="checkbox" name="maxRedemptions" /></td>
      
      <td class="label">Expiration</td>
      <td>
        <input type="radio" name="expiration" checked /> Never
        <input type="radio" name="expiration" /> Date
        <input type="radio" name="expiration" /> Days
      </td>
    </tr>
  </table>
</#macro>