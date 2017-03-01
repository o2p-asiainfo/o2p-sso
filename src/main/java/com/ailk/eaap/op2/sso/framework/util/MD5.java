/*
 * @(#)MD5.java 1.0 2010-10-29
 *
 * Copyright 2010 Souwo Company, Inc. All rights reserved.
 * SOUWO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ailk.eaap.op2.sso.framework.util;


import java.security.*;

/**
 * The Class MD5.
 * 
 * @author Jiarui Yan
 * @version 1.0, 10-29-2010
 * @see com.souwo.souwoba.util.security.MD5
 * @since JDK1.6
 */
public class MD5 {

    /** The hexs. */
    private static char hexs[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f','g','h','i','j','l','m','n','o',
            'p' ,'q','r','s','t','u','v','w','x','y','z'};

    /**
     * Encode.
     * 
     * @param source
     *                the source
     * 
     * @return the string
     */
    public static String encode(String source,String encoding) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");

            byte[] sbs = source.getBytes(encoding);
            digester.update(sbs);
            byte[] rbs = digester.digest();
            int j = rbs.length;
            char result[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = rbs[i];
                result[k++] = hexs[b >>> 4 & 0xf];
                result[k++] = hexs[b & 0xf];
            }
            return new String(result);
        } catch (Exception e) {
            return null;
        }
    }
    public static String encode(String source) {
        return encode(source,"UTF8");
    }
    public static void main(String[] args) {
    	//String appSecret ="fdd18a707780e40d44335a321ab60056";
      String strmy="<SvcCont><CustOrder><OrderNo>10000120110822002314</OrderNo><PreHandleFlag>C</PreHandleFlag><OrderAmount>2000</OrderAmount><Remarks>客户填写说明</Remarks><InvoiceTitle>终端发票抬头</InvoiceTitle><InvoiceContent>终端发票内容</InvoiceContent><InvoiceMoney>终端发票金额</InvoiceMoney><AcceptTime>2011100101120000</AcceptTime><LanId>1101</LanId><Cust><CustName>张三</CustName><CustCert><CertType>1</CertType><CertNumber>11111111111111</CertNumber><CertAddress>证件地址</CertAddress></CustCert></Cust><OrderContactInfo><ContactName>张三</ContactName><ContactAddr>收货人地址</ContactAddr><PostAreaCode>收货所在地区编码</PostAreaCode><Postalcode>邮政编码</Postalcode><ContactNbr>18911111111</ContactNbr><EMail>abc@189.cn</EMail></OrderContactInfo><OrderItemGroup><OrderItemGroupId>100</OrderItemGroupId><ServiceOfferId>100</ServiceOfferId><OldOrderItemGroupId></OldOrderItemGroupId><ProdCLass>12</ProdCLass><AccNbr>18988888888</AccNbr><!--<ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst><ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst>--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-100$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010003</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-101$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010105</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-102$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010079</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-103$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010078</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-104$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010014</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-105$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010011</ProdOfferCode></ProdOfferInst><MktResInst><ActionCd>A</ActionCd><MktResCd>300462</MktResCd><!--营销资源分类,101000手机终端--><MktResType></MktResType><MktResName>华为C7300黑色</MktResName><!--品牌--><TermManf>华为</TermManf><!--机型--><TermModel>C7300</TermModel><!--颜色--><TermColor>黑色</TermColor><UnitPrice>200000</UnitPrice><Quantity>1</Quantity></MktResInst><!--终端购机费--><AcctItem><AcctItemTypeId>2003000</AcctItemTypeId><Amount>200000</Amount><RealAmount>200000</RealAmount></AcctItem><!--预存话费款--><AcctItem><AcctItemTypeId>2080000</AcctItemTypeId><Amount>15000</Amount><RealAmount>15000</RealAmount></AcctItem><!--终端补贴款--><AcctItem><AcctItemTypeId>2090000</AcctItemTypeId><Amount>10000</Amount><RealAmount>10000</RealAmount></AcctItem><!--其它--><AcctItem><AcctItemTypeId>2099999</AcctItemTypeId><Amount>20000</Amount><RealAmount>20000</RealAmount></AcctItem></OrderItemGroup></CustOrder></SvcCont>";
      String strLaotie="<SvcCont><CustOrder><OrderNo>10000120110822002314</OrderNo><PreHandleFlag>C</PreHandleFlag><OrderAmount>2000</OrderAmount><Remarks>客户填写说明</Remarks><InvoiceTitle>终端发票抬头</InvoiceTitle><InvoiceContent>终端发票内容</InvoiceContent><InvoiceMoney>终端发票金额</InvoiceMoney><AcceptTime>2011100101120000</AcceptTime><LanId>1101</LanId><Cust><CustName>张三</CustName><CustCert><CertType>1</CertType><CertNumber>11111111111111</CertNumber><CertAddress>证件地址</CertAddress></CustCert></Cust><OrderContactInfo><ContactName>张三</ContactName><ContactAddr>收货人地址</ContactAddr><PostAreaCode>收货所在地区编码</PostAreaCode><Postalcode>邮政编码</Postalcode><ContactNbr>18911111111</ContactNbr><EMail>abc@189.cn</EMail></OrderContactInfo><OrderItemGroup><OrderItemGroupId>100</OrderItemGroupId><ServiceOfferId>100</ServiceOfferId><OldOrderItemGroupId></OldOrderItemGroupId><ProdCLass>12</ProdCLass><AccNbr>18988888888</AccNbr><!--<ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst><ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst>--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-100$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010003</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-101$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010105</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-102$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010079</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-103$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010078</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-104$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010014</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-105$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010011</ProdOfferCode></ProdOfferInst><MktResInst><ActionCd>A</ActionCd><MktResCd>300462</MktResCd><!--营销资源分类,101000手机终端--><MktResType></MktResType><MktResName>华为C7300黑色</MktResName><!--品牌--><TermManf>华为</TermManf><!--机型--><TermModel>C7300</TermModel><!--颜色--><TermColor>黑色</TermColor><UnitPrice>200000</UnitPrice><Quantity>1</Quantity></MktResInst><!--终端购机费--><AcctItem><AcctItemTypeId>2003000</AcctItemTypeId><Amount>200000</Amount><RealAmount>200000</RealAmount></AcctItem><!--预存话费款--><AcctItem><AcctItemTypeId>2080000</AcctItemTypeId><Amount>15000</Amount><RealAmount>15000</RealAmount></AcctItem><!--终端补贴款--><AcctItem><AcctItemTypeId>2090000</AcctItemTypeId><Amount>10000</Amount><RealAmount>10000</RealAmount></AcctItem><!--其它--><AcctItem><AcctItemTypeId>2099999</AcctItemTypeId><Amount>20000</Amount><RealAmount>20000</RealAmount></AcctItem></OrderItemGroup></CustOrder></SvcCont>";            
      String str = "1000000052201101010000000022"+strmy + "fdd18a707780e40d44335a321ab60056";
    //	String ssss="1000000052201110010000000017<SvcCont><CustOrder><OrderNo>10000120110822002314</OrderNo><PreHandleFlag>C</PreHandleFlag><OrderAmount>2000</OrderAmount><Remarks>客户填写说明</Remarks><InvoiceTitle>终端发票抬头</InvoiceTitle><InvoiceContent>终端发票内容</InvoiceContent><InvoiceMoney>终端发票金额</InvoiceMoney><AcceptTime>2011100101120000</AcceptTime><LanId>1101</LanId><Cust><CustName>张三</CustName><CustCert><CertType>1</CertType><CertNumber>11111111111111</CertNumber><CertAddress>证件地址</CertAddress></CustCert></Cust><OrderContactInfo><ContactName>张三</ContactName><ContactAddr>收货人地址</ContactAddr><PostAreaCode>收货所在地区编码</PostAreaCode><Postalcode>邮政编码</Postalcode><ContactNbr>18911111111</ContactNbr><EMail>abc@189.cn</EMail></OrderContactInfo><OrderItemGroup><OrderItemGroupId>100</OrderItemGroupId><ServiceOfferId>100</ServiceOfferId><OldOrderItemGroupId></OldOrderItemGroupId><ProdCLass>12</ProdCLass><AccNbr>18988888888</AccNbr><!--<ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst><ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst>--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-100$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010003</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-101$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010105</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-102$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010079</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-103$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010078</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-104$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010014</ProdOfferCode></ProdOfferInst><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-105$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010011</ProdOfferCode></ProdOfferInst><MktResInst><ActionCd>A</ActionCd><MktResCd>300462</MktResCd><!--营销资源分类,101000手机终端--><MktResType></MktResType><MktResName>华为C7300黑色</MktResName><!--品牌--><TermManf>华为</TermManf><!--机型--><TermModel>C7300</TermModel><!--颜色--><TermColor>黑色</TermColor><UnitPrice>200000</UnitPrice><Quantity>1</Quantity></MktResInst><!--终端购机费--><AcctItem><AcctItemTypeId>2003000</AcctItemTypeId><Amount>200000</Amount><RealAmount>200000</RealAmount></AcctItem><!--预存话费款--><AcctItem><AcctItemTypeId>2080000</AcctItemTypeId><Amount>15000</Amount><RealAmount>15000</RealAmount></AcctItem><!--终端补贴款--><AcctItem><AcctItemTypeId>2090000</AcctItemTypeId><Amount>10000</Amount><RealAmount>10000</RealAmount></AcctItem><!--其它--><AcctItem><AcctItemTypeId>2099999</AcctItemTypeId><Amount>20000</Amount><RealAmount>20000</RealAmount></AcctItem></OrderItemGroup></CustOrder></SvcCont>fdd18a707780e40d44335a321ab60056";
    	//<?xml version="1.0" encoding="UTF-8"?><ContractRoot><TcpCont><!--应用AppKey--><AppKey>24424d3d05c4f751d63089a79fe7b85d</AppKey><Method>orders.orderIssued</Method><!--发起方流水--><TransactionID>1000000051201110010000000001</TransactionID><!--签名--><Sign>33ba903cb811c9cbbeb2dbaeedafdae7</Sign><!--请单时间--><ReqTime>20110501200202</ReqTime></TcpCont><SvcCont><!--客户订单 --><CustOrder><!--第三方门户订单号--><OrderNo>10000120110822002314</OrderNo><!--是否校验单--><PreHandleFlag>F</PreHandleFlag><!--订单总销售金额--><OrderAmount>2000</OrderAmount><Remarks>客户填写说明</Remarks><InvoiceTitle>终端发票抬头</InvoiceTitle><InvoiceContent>终端发票内容</InvoiceContent><InvoiceMoney>终端发票金额</InvoiceMoney><AcceptTime>2011100101120000</AcceptTime><!--本地网标识--><LanId>1101</LanId><!--客户信息--><Cust><CustName>张三</CustName><CustCert><CertType>1</CertType><CertNumber>11111111111111</CertNumber><CertAddress>证件地址</CertAddress></CustCert></Cust><!--收货人信息--><OrderContactInfo><ContactName>张三</ContactName><ContactAddr>收货人地址</ContactAddr><PostAreaCode>收货所在地区编码</PostAreaCode><Postalcode>邮政编码</Postalcode><ContactNbr>18911111111</ContactNbr><EMail>abc@189.cn</EMail></OrderContactInfo><!--订单项分组--><OrderItemGroup><OrderItemGroupId>100</OrderItemGroupId><ServiceOfferId>100</ServiceOfferId><OldOrderItemGroupId></OldOrderItemGroupId><!--产品大类 12天翼手机--><ProdCLass>12</ProdCLass><AccNbr>18988888888</AccNbr><!--订购的功能产品,用于新用户订购不在基础销售品包含的其他功能产品,或老用户订购功能产品 --><!--<ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst><ProdInst><ActionCd>A</ActionCd><ProductCode>235010001</ProductCode></ProdInst>--><!--基础销售品 订购3G乐享全能版89礼包--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-100$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010003</ProdOfferCode></ProdOfferInst><!--可选包 增值业务6选3 音乐下载--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-101$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010105</ProdOfferCode></ProdOfferInst><!--可选包 增值业务6选3 天翼视讯全能看--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-102$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010079</ProdOfferCode></ProdOfferInst><!--可选包 增值业务6选3 天翼空间全能玩--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-103$</ProdOfferInstId><ParentProdOfferInstId>$-100$</ParentProdOfferInstId><ProdOfferCode>135010078</ProdOfferCode></ProdOfferInst><!--其他增值销售品 189邮箱5元/月--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-104$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010014</ProdOfferCode></ProdOfferInst><!--乐享3G 8000话费大礼包--><ProdOfferInst><ActionCd>A</ActionCd><ProdOfferInstId>$-105$</ProdOfferInstId><ParentProdOfferInstId></ParentProdOfferInstId><ProdOfferCode>135010011</ProdOfferCode></ProdOfferInst><!--营销资源实例--><MktResInst><ActionCd>A</ActionCd><!-- 营销资源编码，对于第三门户自有销售的终端，此字段不填--><MktResCd>300462</MktResCd><!--营销资源分类,101000手机终端--><MktResType></MktResType><MktResName>华为C7300黑色</MktResName><!--品牌--><TermManf>华为</TermManf><!--机型--><TermModel>C7300</TermModel><!--颜色--><TermColor>黑色</TermColor><UnitPrice>200000</UnitPrice><Quantity>1</Quantity></MktResInst><!--终端购机费--><AcctItem><AcctItemTypeId>2003000</AcctItemTypeId><Amount>200000</Amount><RealAmount>200000</RealAmount></AcctItem><!--预存话费款--><AcctItem><AcctItemTypeId>2080000</AcctItemTypeId><Amount>15000</Amount><RealAmount>15000</RealAmount></AcctItem><!--终端补贴款--><AcctItem><AcctItemTypeId>2090000</AcctItemTypeId><Amount>10000</Amount><RealAmount>10000</RealAmount></AcctItem><!--其它--><AcctItem><AcctItemTypeId>2099999</AcctItemTypeId><Amount>20000</Amount><RealAmount>20000</RealAmount></AcctItem></OrderItemGroup></CustOrder></SvcCont></ContractRoot>
    }
}