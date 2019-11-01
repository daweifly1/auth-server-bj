package yb.ecp.fast.user.infra;

import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.RoleUserVO;

public enum ErrorCode {

   FailedToVerifyCode(RoleMenuVO.ALLATORIxDEMO("c>n2fmg%k1{m3g"), 19, 19, RoleUserVO.ALLATORIxDEMO("髱讧硼长讒")),
   Success(RoleMenuVO.ALLATORIxDEMO("Q\"a4g$q"), 0, 0, RoleUserVO.ALLATORIxDEMO("撫伡扶勢")),
   UserNameExists(RoleMenuVO.ALLATORIxDEMO("q2pc:gz>q#q"), 8, 8, RoleUserVO.ALLATORIxDEMO("畕扑幭厑嶏嬾坕")),
   AppAddFail(RoleMenuVO.ALLATORIxDEMO("r\'C3fc>n"), 34, 1101, RoleUserVO.ALLATORIxDEMO("廲畕剽庇套赘")),
   TemplateInUse(RoleMenuVO.ALLATORIxDEMO("V2o\'n6v2K9W$g"), 30, 32, RoleUserVO.ALLATORIxDEMO("朥阭橇朂裍伂畎乐")),
   OAuthResponseTypeNotNull(RoleMenuVO.ALLATORIxDEMO("C\"v?P2q\'m9q2V.r2L8vw;n"), 38, 1103, RoleUserVO.ALLATORIxDEMO("\b\"厤攍乫肀乜稇"));
   // $FF: synthetic field
   private static final ErrorCode[] L;
   OAuthGrantTypeNotNull(RoleMenuVO.ALLATORIxDEMO("Mw#jp6l#V.r2L8vw;n"), 40, 1105, RoleUserVO.ALLATORIxDEMO("\"厤攍乫肀乜稇")),
   TemplateIsNull(RoleMenuVO.ALLATORIxDEMO("g:r;c#gqw;n"), 24, 24, RoleUserVO.ALLATORIxDEMO("橜朙侜怉买寪攉")),
   YourErrorCodeGoesHere(RoleMenuVO.ALLATORIxDEMO("[8w%G%p8pm3gm2qg%g"), 33, 1000, RoleUserVO.ALLATORIxDEMO("伝盢奌赃硼讑坕周霟导临")),
   OAuthAppIdInvalid(RoleMenuVO.ALLATORIxDEMO("Mw#jr\'K3K9t6n>f"), 41, 1104, RoleUserVO.ALLATORIxDEMO("斝攮盹\r\"")),
   CannotRemoveYouself(RoleMenuVO.ALLATORIxDEMO("A6l9m#P2o8t2[8w$g;d"), 29, 29, RoleUserVO.ALLATORIxDEMO("乫肀剆阙畎扊膌嶌")),
   OAuthAddAuthRecordFail(RoleMenuVO.ALLATORIxDEMO("C\"v?C3fw#jg4m%fc>n"), 49, 1108, RoleUserVO.ALLATORIxDEMO("畹扭揮朾诖弨套赘")),
   OAuthCodeInvalid(RoleMenuVO.ALLATORIxDEMO("C\"v?A8f2K9t6n>f"), 46, 1104, RoleUserVO.ALLATORIxDEMO("斆攵盢揵朥硼")),
   SqlSyntaxError(RoleMenuVO.ALLATORIxDEMO("s;Q.l#c/G%p8p"), 14, 14, RoleUserVO.ALLATORIxDEMO("攍挈廮戁蠱彤幅")),
   CheckLoginFailure(RoleMenuVO.ALLATORIxDEMO("A?g4im0k9D6k;w%g"), 7, 7, RoleUserVO.ALLATORIxDEMO("畕扑买嬾坕扰聸宠硼长讒")),
   OAuthRedirectUrlInvalid(RoleMenuVO.ALLATORIxDEMO("Mw#jg3k%g4vp;K9t6n>f"), 42, 1105, RoleUserVO.ALLATORIxDEMO("斝攮盹\t9\b")),
   AdminCannotRemove(RoleMenuVO.ALLATORIxDEMO("C3o>lc9l8vg:m!g"), 31, 33, RoleUserVO.ALLATORIxDEMO("粝红篇瑻吾买厉亘剆阙")),
   OAuthGenToeknFail(RoleMenuVO.ALLATORIxDEMO("Mw#jg9V8g<lc>n"), 50, 1108, RoleUserVO.ALLATORIxDEMO("畢扶揵朥硼套赘")),
   SQLIntegrityConstraintViolation(RoleMenuVO.ALLATORIxDEMO("QNl#g0p>v.A8l$v%c>l#T>m;c#k8l"), 5, 5, RoleUserVO.ALLATORIxDEMO("辠厫寱攒恚绀朢")),
   UnExceptedError(RoleMenuVO.ALLATORIxDEMO("W9G/a2r#g3G%p8p"), 3, 3, RoleUserVO.ALLATORIxDEMO("杗瞃盹长讒厷畢")),
   NoAuthorization(RoleMenuVO.ALLATORIxDEMO("L8C\"v?m%k-c#k8l"), 6, 6, RoleUserVO.ALLATORIxDEMO("泜杯朾阶戚蠪欙撫伡")),
   RoleIsUsed(RoleMenuVO.ALLATORIxDEMO("m;gqq2f"), 10, 10, RoleUserVO.ALLATORIxDEMO("覯舔嶏纩裖伙畕")),
   FailedToNewCode(RoleMenuVO.ALLATORIxDEMO("D6k;g3V8L2um3g"), 27, 27, RoleUserVO.ALLATORIxDEMO("红纺杜柹罰厊畹扭套赘")),
   OAuthAppIdNotNull(RoleMenuVO.ALLATORIxDEMO("Mw#jr\'K3L8vw;n"), 36, 1101, RoleUserVO.ALLATORIxDEMO("\r\"厤攍乫肀乜稇"));
   private String e;
   OAuthAppSecretNotNUll(RoleMenuVO.ALLATORIxDEMO("Mw#jr\'Q2a%g#L8vW;n"), 39, 1104, RoleUserVO.ALLATORIxDEMO("\r\"\t厤攍乫肀乜稇")),
   NoPermissionForThisSite(RoleMenuVO.ALLATORIxDEMO("L8R2p:k$q>m9D8pj>qk#g"), 25, 25, RoleUserVO.ALLATORIxDEMO("泜杯朾阶瘆弳讘纮窒")),
   OAuthAppSecretInvalid(RoleMenuVO.ALLATORIxDEMO("Mw#jr\'Q2a%g#K9t6n>f"), 44, 1104, RoleUserVO.ALLATORIxDEMO("斝攮盹\r\"\t")),
   PwdAlreadSet(RoleMenuVO.ALLATORIxDEMO("u3C;p2c3Q2v"), 13, 13, RoleUserVO.ALLATORIxDEMO("讘畎扊嶔纲诘缓宠硼")),
   OAuthGenAuthCodeFail(RoleMenuVO.ALLATORIxDEMO("C\"v?E2lw#jm3gc>n"), 48, 1107, RoleUserVO.ALLATORIxDEMO("畢扶揵朥硼套赘")),
   FailedToCacheAuthCode(RoleMenuVO.ALLATORIxDEMO("D6k;g3V8A6a?gw#jm3g"), 21, 21, RoleUserVO.ALLATORIxDEMO("罵嬥畎扊朥阭套赘")),
   UserLocked(RoleMenuVO.ALLATORIxDEMO("q2pm4i2f"), 11, 11, RoleUserVO.ALLATORIxDEMO("畕扑裖镧寧")),
   OAuthRefreshTokenInvalid(RoleMenuVO.ALLATORIxDEMO("C\"v?P2d%g$jm<g9K9t6n>f"), 47, 1104, RoleUserVO.ALLATORIxDEMO("斝攮盹剑旍亂爱")),
   OldPwdNotRight(RoleMenuVO.ALLATORIxDEMO("n3R fm#P>e?v"), 12, 12, RoleUserVO.ALLATORIxDEMO("叹宻硧买欅砓")),
   FailedToInsertRecord(RoleMenuVO.ALLATORIxDEMO("c>n2fml$g%vg4m%f"), 15, 15, RoleUserVO.ALLATORIxDEMO("掴儘攖挓诖弨套赘")),
   FailedToUpdateRecord(RoleMenuVO.ALLATORIxDEMO("c>n2fmr3c#gg4m%f"), 17, 17, RoleUserVO.ALLATORIxDEMO("暒旍攖挓诖弨套赘")),
   FailedToGetSession(RoleMenuVO.ALLATORIxDEMO("c>n2fmg#Q2q$k8l"), 26, 26, RoleUserVO.ALLATORIxDEMO("菑厫罵嬥侇怒套赘")),
   OAuthRedirectUrlNotNull(RoleMenuVO.ALLATORIxDEMO("Mw#jg3k%g4vp;L8vw;n"), 37, 1102, RoleUserVO.ALLATORIxDEMO("\t9\b厤攍乫肀乜稇")),
   IllegalArument(RoleMenuVO.ALLATORIxDEMO("n;g0c;C%w:g9v"), 4, 4, RoleUserVO.ALLATORIxDEMO("厤攍长讒")),
   OAuthResponseTypeInvalid(RoleMenuVO.ALLATORIxDEMO("C\"v?P2q\'m9q2V.r2K9t6n>f"), 43, 1106, RoleUserVO.ALLATORIxDEMO("斆攵盢\b\"")),
   FailedToRemoveRecord(RoleMenuVO.ALLATORIxDEMO("c>n2fmg:m!gg4m%f"), 16, 16, RoleUserVO.ALLATORIxDEMO("剆阙攖挓诖弨套赘")),
   Failure(RoleMenuVO.ALLATORIxDEMO("D6k;w%g"), 1, 1, RoleUserVO.ALLATORIxDEMO("撫伡套赘")),
   RoleNameExists(RoleMenuVO.ALLATORIxDEMO("m;gc:gz>q#q"), 9, 9, RoleUserVO.ALLATORIxDEMO("覴舏呫嶏嬾坕")),
   FailedToRetreiveRecord(RoleMenuVO.ALLATORIxDEMO("c>n2fmg#p2k!gg4m%f"), 18, 18, RoleUserVO.ALLATORIxDEMO("菑厫攖挓诖弨套赘")),
   CannotEditSystemData(RoleMenuVO.ALLATORIxDEMO("c9l8vf>v{$v2oc#c"), 28, 28, RoleUserVO.ALLATORIxDEMO("粆纹剠妭卫攖挓斆沨罰迬")),
   DeptNameExist(RoleMenuVO.ALLATORIxDEMO("F2r#L6o2G/k$v"), 23, 23, RoleUserVO.ALLATORIxDEMO("呫禍嶔裖伙畕")),
   AppNotExist(RoleMenuVO.ALLATORIxDEMO("C\'rm#G/k$v"), 35, 1102, RoleUserVO.ALLATORIxDEMO("廩畎买嬾坕")),
   NeedLogin(RoleMenuVO.ALLATORIxDEMO("L2g3N8e>l"), 2, 2, RoleUserVO.ALLATORIxDEMO("畎扊泇杴瘝弨"));
   private int ALLATORIxDEMO;
   MobileExistError(RoleMenuVO.ALLATORIxDEMO("m5k;gz>q#G%p8p"), 32, 34, RoleUserVO.ALLATORIxDEMO("戶杜厊硧嶏嬾坕")),
   FailedToCacheUserDate(RoleMenuVO.ALLATORIxDEMO("D6k;g3V8A6a?gq2pc#g"), 20, 20, RoleUserVO.ALLATORIxDEMO("罵嬥畎扊侇怒套赘")),
   FailedToRenewLeaseSession(RoleMenuVO.ALLATORIxDEMO("D6k;g3V8P2l2ug6q2Q2q$k8l"), 22, 22, RoleUserVO.ALLATORIxDEMO("暉旖.#.54)3套赘")),
   OAuthGrantTypeInvalid(RoleMenuVO.ALLATORIxDEMO("Mw#jp6l#V.r2K9t6n>f"), 45, 1104, RoleUserVO.ALLATORIxDEMO("斝攮盹\""));


   static {
      ErrorCode[] var10000 = new ErrorCode[51];
      boolean var10002 = true;
      ((Object[])true)[0] = (boolean)Success;
      ((Object[])true)[1] = (boolean)Failure;
      ((Object[])true)[2] = (boolean)NeedLogin;
      ((Object[])true)[3] = (boolean)UnExceptedError;
      ((Object[])true)[4] = (boolean)IllegalArument;
      ((Object[])true)[5] = (boolean)SQLIntegrityConstraintViolation;
      ((Object[])true)[6] = (boolean)NoAuthorization;
      ((Object[])true)[7] = (boolean)CheckLoginFailure;
      ((Object[])true)[8] = (boolean)UserNameExists;
      ((Object[])true)[9] = (boolean)RoleNameExists;
      ((Object[])true)[10] = (boolean)RoleIsUsed;
      ((Object[])true)[11] = (boolean)UserLocked;
      ((Object[])true)[12] = (boolean)OldPwdNotRight;
      ((Object[])true)[13] = (boolean)PwdAlreadSet;
      ((Object[])true)[14] = (boolean)SqlSyntaxError;
      ((Object[])true)[15] = (boolean)FailedToInsertRecord;
      ((Object[])true)[16] = (boolean)FailedToRemoveRecord;
      ((Object[])true)[17] = (boolean)FailedToUpdateRecord;
      ((Object[])true)[18] = (boolean)FailedToRetreiveRecord;
      ((Object[])true)[19] = (boolean)FailedToVerifyCode;
      ((Object[])true)[20] = (boolean)FailedToCacheUserDate;
      ((Object[])true)[21] = (boolean)FailedToCacheAuthCode;
      ((Object[])true)[22] = (boolean)FailedToRenewLeaseSession;
      ((Object[])true)[23] = (boolean)DeptNameExist;
      ((Object[])true)[24] = (boolean)TemplateIsNull;
      ((Object[])true)[25] = (boolean)NoPermissionForThisSite;
      ((Object[])true)[26] = (boolean)FailedToGetSession;
      ((Object[])true)[27] = (boolean)FailedToNewCode;
      ((Object[])true)[28] = (boolean)CannotEditSystemData;
      ((Object[])true)[29] = (boolean)CannotRemoveYouself;
      ((Object[])true)[30] = (boolean)TemplateInUse;
      ((Object[])true)[31] = (boolean)AdminCannotRemove;
      ((Object[])true)[32] = (boolean)MobileExistError;
      ((Object[])true)[33] = (boolean)YourErrorCodeGoesHere;
      ((Object[])true)[34] = (boolean)AppAddFail;
      ((Object[])true)[35] = (boolean)AppNotExist;
      ((Object[])true)[36] = (boolean)OAuthAppIdNotNull;
      ((Object[])true)[37] = (boolean)OAuthRedirectUrlNotNull;
      ((Object[])true)[38] = (boolean)OAuthResponseTypeNotNull;
      ((Object[])true)[39] = (boolean)OAuthAppSecretNotNUll;
      ((Object[])true)[40] = (boolean)OAuthGrantTypeNotNull;
      ((Object[])true)[41] = (boolean)OAuthAppIdInvalid;
      ((Object[])true)[42] = (boolean)OAuthRedirectUrlInvalid;
      ((Object[])true)[43] = (boolean)OAuthResponseTypeInvalid;
      ((Object[])true)[44] = (boolean)OAuthAppSecretInvalid;
      ((Object[])true)[45] = (boolean)OAuthGrantTypeInvalid;
      ((Object[])true)[46] = (boolean)OAuthCodeInvalid;
      ((Object[])true)[47] = (boolean)OAuthRefreshTokenInvalid;
      ((Object[])true)[48] = (boolean)OAuthGenAuthCodeFail;
      ((Object[])true)[49] = (boolean)OAuthAddAuthRecordFail;
      ((Object[])true)[50] = (boolean)OAuthGenToeknFail;
      L = true;
   }

   public String getDesc() {
      return a.e;
   }

   // $FF: synthetic method
   private ErrorCode(String var1, int var2, int a1, String a2) {
      a.e = a2;
      a.ALLATORIxDEMO = a1;
   }

   public int getCode() {
      return a.ALLATORIxDEMO;
   }
}
