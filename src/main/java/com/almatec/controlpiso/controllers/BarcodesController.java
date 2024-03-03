package com.almatec.controlpiso.controllers;


import java.awt.image.BufferedImage;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almatec.controlpiso.utils.BarcodeGenerator;


@RestController
@RequestMapping("/barcodes")
public class BarcodesController {
	
	@GetMapping(value = "/barbecue/code128/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return ResponseEntity.ok(BarcodeGenerator.generateCode128BarcodeImage(barcode));
    }
	
	@GetMapping(value = "/zxing/code128/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingCode128Barcode(@PathVariable String barcode) throws Exception {
        return ResponseEntity.ok(BarcodeGenerator.generateQRCodeImage(barcode));
    }

}
