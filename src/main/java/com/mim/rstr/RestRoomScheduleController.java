package com.mim.rstr;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mim.service.RestRoomService;
import com.mim.util.GeocoderUtil;
import com.mim.util.XlsxUtil;

/**
 * �������� �����Ѵ�.
 */
@RestController
@RequestMapping(value = "/rstr")
public class RestRoomScheduleController
{
	@Autowired
	private RestRoomService service;
	@Autowired
	private GeocoderUtil geocoder;

	/**
	 * insert �������� �����Ѵ�.
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@RequestMapping(value = "/job.do", method = RequestMethod.GET)
	public void excel() throws IOException, InvalidFormatException
	{
		/*String[] fileNames = {
			"����Ư����",
			"�λ걤����",
			"�뱸������",
			"��õ������",
			"���ֱ�����",
			"����������",
			"��걤����",
			"����Ư����ġ��",
			"��⵵",
			"������",
			"��û�ϵ�",
			"��û����",
			"����ϵ�",
			"���󳲵�",
			"���ϵ�",
			"��󳲵�",
			"����Ư����ġ��"};*/
		String[] fileNames = {"����Ư����", "�λ걤����", "���󳲵�", "��󳲵�"};
		for (String fileName : fileNames)
		{
			System.out.println("**** " + fileName + " START");
			excelRegister(fileName);
			System.out.println("**** " + fileName + " END");
		}

		try
		{
			//	updateNaverGeo();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ������ �����͸� db�� insert�Ѵ�.
	 * @param fileName
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void excelRegister(String fileName) throws IOException, InvalidFormatException
	{
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD");
		File file = new File("D:\\rstr_excel\\" + fileName + ".xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int startRow = 1;
		int totalRowNum = sheet.getPhysicalNumberOfRows();
		System.out.println("totalRowNum = " + totalRowNum);

		List<RestRoom> list = new ArrayList<RestRoom>();
		List<String> nameList = new ArrayList<String>();
		for (int i = startRow; i < totalRowNum; i++)
		{
			XSSFRow row = sheet.getRow(i);
			if (null != row)
			{
				RestRoom rstr = new RestRoom();
				String name = getRstrCellValue(row.getCell(2));

				//�ߺ��̸� ����ó��
				if (nameList.contains(name))
				{
					rstr.setIsvisb(0);
				}
				else
				{
					rstr.setIsvisb(1);
				}
				nameList.add(name);

				rstr.setType(getRstrCellValue(row.getCell(1)));
				rstr.setName(name);

				rstr.setRdnmAdr(getRstrCellValue(row.getCell(3)));
				rstr.setLnmAdr(getRstrCellValue(row.getCell(4)));

				String unisex = getRstrCellValue(row.getCell(5));
				rstr.setUnisexYn(StringUtils.equalsIgnoreCase(unisex, "Y") ? 1 : 0);

				rstr
					.setMenBowlNum(
						getRstrCellValue(row.getCell(6)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(6))));
				rstr
					.setMenUrinalNum(
						getRstrCellValue(row.getCell(7)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(7))));
				rstr
					.setMenHandicapBowlNum(
						getRstrCellValue(row.getCell(8)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(8))));
				rstr
					.setMenHandicapUrinalNum(
						getRstrCellValue(row.getCell(9)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(9))));
				rstr
					.setMenChildrenBowlNum(
						getRstrCellValue(row.getCell(10)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(10))));
				rstr
					.setMenChildrenUrinalNum(
						getRstrCellValue(row.getCell(11)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(11))));

				rstr
					.setLadiesBowlNum(
						getRstrCellValue(row.getCell(12)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(12))));
				rstr
					.setLadiesHandicapBowlNum(
						getRstrCellValue(row.getCell(13)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(13))));
				rstr
					.setLadiesChildToiletBowlNum(
						getRstrCellValue(row.getCell(14)) == null
							? 0
							: Integer.parseInt(getRstrCellValue(row.getCell(14))));

				rstr.setInstitutionName(getRstrCellValue(row.getCell(15))); //���������
				rstr.setPhonNum(getRstrCellValue(row.getCell(16))); //��ȭ��ȣ
				rstr.setOpenTime(getRstrCellValue(row.getCell(17))); //����ð�
				rstr.setInstallationYear(getRstrCellValue(row.getCell(18))); //��ġ����

				rstr.setLatitude(getRstrCellValue(row.getCell(19))); //����
				rstr.setLongitude(getRstrCellValue(row.getCell(20)));//�浵

				rstr.setPossType(getRstrCellValue(row.getCell(21))); //��������
				rstr.setPosiType(getRstrCellValue(row.getCell(22)));//��ġ�������

				//����ó�� ��� 23

				rstr.setEmgBell(getRstrCellValue(row.getCell(24))); //��󺧼�ġ����

				String cctv = getRstrCellValue(row.getCell(25));
				rstr.setCctvYn(StringUtils.equalsIgnoreCase(cctv, "Y") ? 1 : 0);

				rstr.setDipersExchgPosi(getRstrCellValue(row.getCell(26))); // ��ȯ�����
				rstr.setModYear(getRstrCellValue(row.getCell(27))); //���𵨸�����

				try
				{
					rstr.setApiDate(sf.parse(getRstrCellValue(row.getCell(28)))); // �����ͱ�����
				}
				catch (ParseException e)
				{
				}
				list.add(rstr);
			}
		}
		service.register(list);

		workbook.close();
	}

	/**
	 * ����ȭ���ǥ�ص������� ��Ŀ� �°� cell Value�� �����´�.
	 * @param cell
	 * @return
	 */
	public String getRstrCellValue(XSSFCell cell)
	{
		String value = XlsxUtil.getCellValue(cell, true);
		if (value.indexOf("����") > -1 || StringUtils.isBlank(value))
		{
			value = null;
		}
		return value;
	}

	/**
	 * ����, �浵�� ���� ���� ���̹�geo�� ������Ʈ�Ѵ�.
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateNaverGeo.do", method = RequestMethod.GET)
	public void updateNaverGeo() throws Exception
	{
		long beforeTime = System.currentTimeMillis();

		List<RestRoom> list = service.listToGeoUpdate();
		int totalCount = list.size();

		System.out.println("rstr updateNaverGeo Start / totalCount = " + totalCount);
		List<RestRoom> nList = new ArrayList<RestRoom>();
		for (int i = 0; i < list.size(); i++)
		{
			RestRoom rstr = list.get(i);
			String name = StringUtils.isNotBlank(rstr.getRdnmAdr()) ? rstr.getRdnmAdr() : rstr.getLnmAdr();

			if (StringUtils.isNotBlank(name))
			{
				String[] naver = geocoder.geocoding(name);
				rstr.setLongitude(naver[0]);
				rstr.setLatitude(naver[1]);
				System.out.println(rstr.getId());
				nList.add(rstr);
			}

			if ((i + 1) % 100 == 0)
			{
				service.mergeGeo(nList);
				nList = new ArrayList<RestRoom>();
				System.out.println("---- " + i+1);
			}
		}
		service.mergeGeo(nList);

		long afterTime = System.currentTimeMillis(); // �ڵ� ���� �Ŀ� �ð� �޾ƿ���
		long secDiffTime = (afterTime - beforeTime) / 1000; //�� �ð��� �� ���
		System.out.println("rstr updateNaverGeo Start / time = " + secDiffTime);
	}
}
